# ECsite
ECsite


デザインサンプル
https://mawal.jp/products/spoon-s-yogan

https://sankoudesign.com/category/ec-onlineshop/

https://webdesigngarden.com/category/industry/toy/

https://webdesigngarden.com/category/impression/animation/



デザイン参考資料

https://bandai-hobby.net/gunpla/

https://p-bandai.jp/gundambase/

https://www.1999.co.jp/

https://kawasaki-onlineshop.jp/shop/default.aspx

https://www.logos.ne.jp/products/info/11572#



               リレーションの一覧
 スキーマ |      名前      |    型    |  所有者
----------+----------------+----------+----------
 public   | account        | テーブル | postgres
 public   | order_customer | テーブル | postgres
 public   | order_history  | テーブル | postgres
 public   | shohin         | テーブル | postgres
 public   | shopping_cart  | テーブル | postgres


account
                                       テーブル "public.account"
       列        |           型           |                           修飾語
-----------------+------------------------+------------------------------------------------------------
 kaiin_id        | integer                | not null default nextval('account_kaiin_id_seq'::regclass)
 password        | character varying(255) | not null
 shimei          | character varying(100) | not null
 yuubin_bangou   | character varying(10)  | not null
 address         | character varying(255) | not null
 denwa_bangou    | character varying(20)  | not null
 seinengappi     | date                   | not null
 mail_address    | character varying(255) | not null
 shiharai_houhou | character varying(50)  | not null
 is_admin        | boolean                | default false
インデックス:
    "account_pkey" PRIMARY KEY, btree (kaiin_id)
参照元：
    TABLE "order_customer" CONSTRAINT "order_customer_kaiin_id_fkey" FOREIGN KEY (kaiin_id) REFERENCES account(kaiin_id)    TABLE "order_history" CONSTRAINT "order_history_kaiin_id_fkey" FOREIGN KEY (kaiin_id) REFERENCES account(kaiin_id)
    TABLE "shopping_cart" CONSTRAINT "shopping_cart_kaiin_id_fkey" FOREIGN KEY (kaiin_id) REFERENCES account(kaiin_id)

order_customer
                                         テーブル "public.order_customer"
       列        |             型              |                              修飾語
-----------------+-----------------------------+-------------------------------------------------------------------
 order_id        | integer                     | not null default nextval('order_customer_order_id_seq'::regclass)
 kaiin_id        | integer                     |
 shimei          | character varying(100)      | not null
 yuubin_bangou   | character varying(10)       | not null
 address         | character varying(255)      | not null
 denwa_bangou    | character varying(20)       | not null
 mail_address    | character varying(255)      | not null
 kounyubi        | timestamp without time zone | default now()
 shiharai_houhou | character varying(50)       | not null
インデックス:
    "order_customer_pkey" PRIMARY KEY, btree (order_id)
外部キー制約:
    "order_customer_kaiin_id_fkey" FOREIGN KEY (kaiin_id) REFERENCES account(kaiin_id)
    

order_history
                                        テーブル "public.order_history"
     列     |             型              |                               修飾語
------------+-----------------------------+--------------------------------------------------------------------
 history_id | integer                     | not null default nextval('order_history_history_id_seq'::regclass)
 kaiin_id   | integer                     | not null
 shohin_id  | integer                     | not null
 quantity   | integer                     | not null default 1
 order_time | timestamp without time zone | default now()
インデックス:
    "order_history_pkey" PRIMARY KEY, btree (history_id)
外部キー制約:
    "order_history_kaiin_id_fkey" FOREIGN KEY (kaiin_id) REFERENCES account(kaiin_id)
    "order_history_shohin_id_fkey" FOREIGN KEY (shohin_id) REFERENCES shohin(shohin_id)


shohin
                                        テーブル "public.shohin"
        列        |           型           |                           修飾語
------------------+------------------------+------------------------------------------------------------
 shohin_id        | integer                | not null default nextval('shohin_shohin_id_seq'::regclass)
 shouhin_mei      | character varying(100) | not null
 shouhin_setsumei | text                   | not null
 kakaku           | integer                | not null
 zaiko_suuryou    | integer                | not null
 shouhin_gazou    | character varying(255) |
 category_name    | character varying(50)  |
インデックス:
    "shohin_pkey" PRIMARY KEY, btree (shohin_id)
    "shouhin_mei" UNIQUE CONSTRAINT, btree (shouhin_mei)
    "unique_shouhin_mei" UNIQUE CONSTRAINT, btree (shouhin_mei)
参照元：
    TABLE "order_history" CONSTRAINT "order_history_shohin_id_fkey" FOREIGN KEY (shohin_id) REFERENCES shohin(shohin_id)    TABLE "shopping_cart" CONSTRAINT "shopping_cart_shohin_id_fkey" FOREIGN KEY (shohin_id) REFERENCES shohin(shohin_id)


shopping_cart
                                      テーブル "public.shopping_cart"
    列     |             型              |                             修飾語
-----------+-----------------------------+-----------------------------------------------------------------
 cart_id   | integer                     | not null default nextval('shopping_cart_cart_id_seq'::regclass)
 kaiin_id  | integer                     | not null
 shohin_id | integer                     | not null
 quantity  | integer                     | not null default 1
 added_at  | timestamp without time zone | default now()
インデックス:
    "shopping_cart_pkey" PRIMARY KEY, btree (cart_id)
外部キー制約:
    "shopping_cart_kaiin_id_fkey" FOREIGN KEY (kaiin_id) REFERENCES account(kaiin_id)
    "shopping_cart_shohin_id_fkey" FOREIGN KEY (shohin_id) REFERENCES shohin(shohin_id)


    


 
