CREATE TABLE task_category (
	task_id bigint NOT NULL,
	category_id bigint NOT NULL,
	CONSTRAINT task_category_pkey PRIMARY KEY (task_id, category_id)
);