-- Project phase 1
-- Triggers
-- Craig Donato - crd69
-- Sam Skupien - sss78
--
-- BoutiqueCoffee database

--drop statments
-- ------------------------------------------------------------------------------------------------------------------
drop trigger if exists PurchaseInsert_1 on CustomerPurchases;
drop function if exists PurchaseInsert();
drop trigger if exists AddReward_1 on buycoffee;
drop function if exists AddRewardPoints();
drop trigger if exists RedeemPoints_1 on buycoffee;
drop function if exists redeempointscheck();
drop view if exists CustomerPurchases;
-- ------------------------------------------------------------------------------------------------------------------

-- CUSTOMERPURCHASES views
-- A view that combines tables PURCHASE, BUYCOFFEE, COFFEE, AND STORE tables
-- to make checking purhcases easier and faster.
-- The view also lets the input of all data for a purchase to be inserted with
-- one insert.
-- ------------------------------------------------------------------------------------------------------------------
create or replace view CustomerPurchases as
select p.purchase_id, p.customer_id, c.first_name, c.last_name, p.store_id, s.name as store_name, b.coffee_id,
       o.name as coffee_name, b.purchase_quantity, b.redeem_quantity, p.purchase_time
from purchase p, buycoffee b, customer c, coffee o, store s
where p.purchase_id = b.purchase_id
    and p.customer_id = c.customer_id
    and b.coffee_id = o.coffee_id
    and p.store_id = s.store_id;

-- ------------------------------------------------------------------------------------------------------------------

-- Trigger for purchases into PURCHASE and BUYCOFFEE table
-- ------------------------------------------------------------------------------------------------------------------
create or replace function PurchaseInsert() returns trigger as
$$
declare
    purchase_temp_id_pur numeric(15,0);
    purchase_temp_id_buy numeric(15,0);
    temp_r_points numeric(15, 0);
    temp_t_points numeric(15, 0);
begin
    select max(purchase_id) into purchase_temp_id_pur
    from purchase;

    select max(purchase_id) into purchase_temp_id_buy
    from buycoffee;

    select redeem_points into temp_r_points
    from coffee f
    where f.coffee_id = new.coffee_id;

    select total_points into temp_t_points
    from customer u
    where u.customer_id = new.customer_id;

    -- This is checking if the PURCHASE table was empty
    if purchase_temp_id_pur is null then
        purchase_temp_id_pur := 0;
    end if;

    -- this is checking if the BUYCOFFEE table was empty
    if purchase_temp_id_buy is null then
        purchase_temp_id_buy := 0;
    end if;

    -- seperates the insert into view CUSTOMERPURCHASE
    -- into inserts into PURCHASE and BUYCOFFEE tables
    -- runs a quick check if a redeem is being done, it makes sure the customer has enough points
    -- before running the inserts.

    if (new.purchase_quantity > 0 and new.redeem_quantity = 0) or
       (new.purchase_quantity = 0 and new.redeem_quantity > 0 and temp_t_points >= temp_r_points) then

        insert into purchase (purchase_id, customer_id, store_id)
        values (purchase_temp_id_pur + 1,new.customer_id, new.store_id);
        insert into buycoffee (purchase_id, coffee_id, purchase_quantity, redeem_quantity)
        values (purchase_temp_id_buy + 1, new.coffee_id, new.purchase_quantity, new.redeem_quantity);

        if purchase_temp_id_pur + 1 <> (select max(purchase_id) from buycoffee) then
            delete from purchase
            where purchase_id = purchase_temp_id_buy + 1;
        end if;

    end if;

    return new;
end;
$$ language plpgsql;

create trigger PurchaseInsert_1
    instead of insert
    on CustomerPurchases
    for each row
execute procedure PurchaseInsert();
-- ------------------------------------------------------------------------------------------------------------------

-- Trigger to add reward points
-- if the customer purchase coffee it will add the existing total_points
-- from the CUSTOMER table plus the reward points from the COFFEE table
-- and then it will add in any additional points from the booster_factor or promotions
-- from the the MEMBERLEVEL and/or PROMOTION table.
-- ------------------------------------------------------------------------------------------------------------------
create or replace function AddRewardPoints() returns trigger as
$$
declare
    temp_pid numeric(15,0);
    temp_reward numeric(15, 0);
    temp_boost numeric(15, 2);
    temp_quantity numeric(15, 0);
    temp_promotion numeric(15, 0) default 0;
begin
    select max(purchase_id) into temp_pid
    from buycoffee;

    if (select purchase_quantity from buycoffee where purchase_id = temp_pid) > 0 and
       (select redeem_quantity from buycoffee where purchase_id = temp_pid) = 0 then

        select f.purchase_quantity into temp_quantity
        from buycoffee f
        where f.purchase_id = temp_pid;

        select o.reward_points into temp_reward
        from coffee o
        where o.coffee_id = (select b.coffee_id
                            from buycoffee b
                            where b.purchase_id = temp_pid);

        select s.booster_factor into temp_boost
        from memberlevel s
        where s.memberlevel_id = (select u.memberlevel_id
                                  from customer u
                                  where u.customer_id = (select p.customer_id
                                                         from purchase p
                                                         where p.purchase_id = temp_pid));

        if (select promotion_id
            from ((promotion natural join promotefor) natural join haspromotion) as promo
            where promo.coffee_id = (select bc.coffee_id from buycoffee bc where bc.purchase_id = 15) and
                  promo.store_id = (select pr.store_id from purchase pr where pr.purchase_id = 15) and
                  promo.start_date <= current_date and
                  promo.end_date >= current_date) is not null then

            temp_promotion = temp_reward;
        end if;

        update customer
        set total_points = total_points + (temp_reward * temp_quantity) + (temp_reward * temp_boost) + temp_promotion
        where customer_id = (select customer_id
                             from purchase
                             where purchase_id = temp_pid);

    end if;

    return new;
end;
$$ language plpgsql;

create trigger AddReward_1
    after insert
    on buycoffee
    for each row
execute procedure AddRewardPoints();
-- ------------------------------------------------------------------------------------------------------------------

-- Trigger to Redeem points
-- checks to see if only the redeem quantity is selected
-- and then it will update the nessesary total points if the customer
-- has enough points.
-- ------------------------------------------------------------------------------------------------------------------
create or replace function RedeemPointsCheck() returns trigger as
$$
declare
    temp_pid numeric (15, 0);
    temp_total_points numeric (15, 0);
    temp_redeem_points numeric (15, 0);
    temp_quantitiy numeric (15, 0);
begin
    select max(purchase_id) into temp_pid
    from buycoffee;

    if (select redeem_quantity from buycoffee where purchase_id = temp_pid) > 0 and
       (select purchase_quantity from buycoffee where purchase_id = temp_pid) = 0 then

        select c.total_points into temp_total_points
        from customer c
        where c.customer_id = (select p.customer_id
                               from purchase p
                               where p.purchase_id = temp_pid);

        select o.redeem_points into temp_redeem_points
        from coffee o
        where o.coffee_id = (select b.coffee_id
                             from buycoffee b
                             where b.purchase_id = temp_pid);

        select u.redeem_quantity into temp_quantitiy
        from buycoffee u
        where u.purchase_id = temp_pid;


        update customer
        set total_points = total_points - (temp_redeem_points * temp_quantitiy)
        where customer_id = (select customer_id
                             from purchase
                             where purchase.purchase_id = temp_pid);
    end if;
    return new;
end;
$$ language plpgsql;

create trigger RedeemPoints_1
    before insert
    on buycoffee
    for each row
execute procedure RedeemPointsCheck();

-- ------------------------------------------------------------------------------------------------------------------
