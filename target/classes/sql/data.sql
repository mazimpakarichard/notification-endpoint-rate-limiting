INSERT INTO public.rate_limits 
(id, client_code, max_requests_per_month, max_requests_per_time_window, time_window_seconds)
VALUES('a87c76f0-0b31-4e20-9721-51c11a16b5d1'::uuid, '00440', 10, 5, 20000);


INSERT INTO public.throttling
(id, delays_ms, server_name, soft_limit)
VALUES('a87c76f0-0b31-4e20-9721-51c11a16b5d7'::uuid, 5, 'server1', 3);
