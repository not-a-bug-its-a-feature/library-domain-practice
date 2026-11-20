create table if not exists member_borrowing_policies (
    id UUID NOT NULL,
    member_type VARCHAR(30) NOT NULL,
    max_borrowable_count INTEGER NOT NULL,

    created_at TIMESTAMPTZ(6) NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ(6) NOT NULL DEFAULT now(),

     constraint pk_member_borrowing_policies PRIMARY KEY (id),
     constraint uk_member_borrowing_policies_type UNIQUE (member_type),
     constraint ck_member_borrowing_policies_max_count CHECK (max_borrowable_count > 0)
);