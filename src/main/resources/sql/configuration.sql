insert
	into
	public.rate_limits 
(id,
	client_code,
	max_requests_per_month,
	max_requests_per_time_window,
	max_global_limit,
	time_window_globally_hour,
	time_window_seconds)
values('a87c76f0-0b31-4e20-9721-51c11a16b5d1'::uuid,
'00440',
10000,
5,
100,
1,
20);

insert
	into
	public.throttling
(id,
	delays_ms,
	server_name,
	soft_limit)
values('a87c76f0-0b31-4e20-9721-51c11a16b5d7'::uuid,
5,
'server1',
3);
