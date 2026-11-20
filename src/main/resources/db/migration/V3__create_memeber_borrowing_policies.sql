create table if not exists member_borrowing_policies (
    member_type_id UUID NOT NULL,
    max_borrowable_count INTEGER NOT NULL,

    created_at TIMESTAMPTZ(6) NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ(6) NOT NULL DEFAULT now(),

     constraint pk_member_borrowing_policies PRIMARY KEY (member_type_id),
     constraint fk_member_borrowing_policies_member_type FOREIGN KEY (member_type_id) REFERENCES member_types (id),
     constraint ck_member_borrowing_policies_max_count CHECK (max_borrowable_count > 0)
);

CREATE TRIGGER trg_users_updated_at
BEFORE UPDATE ON member_borrowing_policies
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();