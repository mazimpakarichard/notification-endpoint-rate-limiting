-- public.rate_limits definition

-- Drop table

-- DROP TABLE public.rate_limits;

CREATE TABLE public.rate_limits (
	id uuid NOT NULL,
	client_code varchar(255) NULL,
	max_requests_per_month int4 NULL,
	max_requests_per_time_window int4 NULL,
	max_global_limit int4 NULL,
	time_window_globally_hour int8 NULL,
	time_window_seconds int8 NULL,
	CONSTRAINT rate_limits_pkey PRIMARY KEY (id)
);

-- public.throttling definition

-- Drop table

-- DROP TABLE public.throttling;

CREATE TABLE public.throttling (
	id uuid NOT NULL,
	delays_ms int4 NULL,
	server_name varchar(255) NULL,
	soft_limit int4 NULL,
	CONSTRAINT throttling_pkey PRIMARY KEY (id)
);