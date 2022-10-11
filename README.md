# Products   
You can open a project in IntelliJ IDEA. Then import the configuration and run the spring boot application.
In the console you can check the response time from the server for our requests, on /data path you can check all the responses from the server that we received from 4 APIs and on /responses path you can check average response time from server where we get the data from.
Localhost is 8081 but you can allways change it in application.properties.

In order for the application to work as it should, it is necessary to create a postgresql database (my database is called products) and a table within that database.

SQL Query:

-- Table: public.responses

-- DROP TABLE IF EXISTS public.responses;

CREATE TABLE IF NOT EXISTS public.responses
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    url character(60) COLLATE pg_catalog."default" NOT NULL,
    request_executed_at character(60) COLLATE pg_catalog."default" NOT NULL,
    response_time integer NOT NULL,
    CONSTRAINT responses_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.responses
    OWNER to postgres;
    


Also in the AverageResponseTime and InsertData class you need to set the correct configuration for you postgresql, such as url:port, username, password.
