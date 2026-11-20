create table if not exists members (
    id UUID NOT NULL,
    external_id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    

    created_at TIMESTAMPTZ(6) NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ(6) NOT NULL DEFAULT now(),

    constraint pk_members PRIMARY KEY (id),
    constraint uk_members_external_id unique (external_id)
);

CREATE TRIGGER trg_users_updated_at
BEFORE UPDATE ON members
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();