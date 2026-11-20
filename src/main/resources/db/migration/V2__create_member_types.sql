create table if not exists member_types (
    id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,

    created_at TIMESTAMPTZ(6) NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ(6) NOT NULL DEFAULT now(),

    constraint pk_member_types PRIMARY KEY (id),
    constraint uk_member_types_name UNIQUE (name)
);

CREATE TRIGGER trg_users_updated_at
BEFORE UPDATE ON member_types
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();