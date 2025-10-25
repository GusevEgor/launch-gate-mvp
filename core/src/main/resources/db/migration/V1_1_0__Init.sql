
create table if not exists users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    user_role VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    bio TEXT,
    created_at TIMESTAMP NOT NULL
);

create table if not exists organisations (
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL
);

create table if not exists competitions (
    id BIGSERIAL PRIMARY KEY,
    is_draft BOOLEAN DEFAULT TRUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    competition_type VARCHAR(255) NOT NULL,
    short_description TEXT,
    registration_start_date BIGINT NOT NULL,
    registration_end_date BIGINT NOT NULL,
    competition_start_date BIGINT NOT NULL,
    competition_end_date BIGINT NOT NULL,
    result_start_date BIGINT NOT NULL,
    result_end_date BIGINT NOT NULL,
    is_public BOOLEAN DEFAULT FALSE NOT NULL,
    format_of_competition VARCHAR(255),
    time_zone VARCHAR(255),
    min_participant_age INTEGER CHECK ( min_participant_age >= 0 ) NOT NULL,
    max_participant_age INTEGER CHECK ( max_participant_age >= 0 ) NOT NULL,
    target_audience VARCHAR(255),
    is_team_required BOOLEAN NOT NULL,
    min_team_size INTEGER CHECK ( min_team_size > 0 ) NOT NULL,
    max_team_size INTEGER CHECK ( max_team_size > 0 ) NOT NULL,
    is_country BOOLEAN NOT NULL

);


create table if not exists media (
    id          BIGSERIAL PRIMARY KEY,
    type_media  VARCHAR(255) NOT NULL,
    url         TEXT NOT NULL,
    filename    VARCHAR(255) NOT NULL,
    metadata    JSONB
);


create table if not exists teams (
    id BIGSERIAL PRIMARY KEY,
    competition_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    members_count SMALLINT DEFAULT 1 CHECK ( members_count > 0 ) NOT NULL,

    constraint fk_teams_competition
        foreign key (competition_id) references competitions(id) ON DELETE CASCADE
);


create table if not exists projects (
    id BIGSERIAL PRIMARY KEY,
    team_id BIGINT NOT NULL,
    description TEXT,

    constraint fk_projects_team
        foreign key (team_id) references teams(id) ON DELETE CASCADE
);


create table if not exists team_user (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    team_id BIGINT NOT NULL,
    role_in_team VARCHAR(255),

    constraint fk_team_user_user
        foreign key (user_id) references users(id) ON DELETE CASCADE,
    constraint fk_team_user_team
        foreign key (team_id) references teams(id) ON DELETE CASCADE
);


create table if not exists competitions_manager (
    id BIGSERIAL PRIMARY KEY,
    competition_id BIGINT NOT NULL,
    user_id BIGINT,
    is_creator BOOLEAN DEFAULT FALSE,
    role_label VARCHAR(255),
    in_system BOOLEAN,

    constraint fk_competition_manager_competition
        foreign key (competition_id) references competitions(id) ON DELETE CASCADE,
    constraint fk_competition_manager_user
        foreign key (user_id) references users(id) ON DELETE CASCADE
);


create table if not exists tags (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    tag_type VARCHAR(255)
);


create table if not exists competitions_tags (
    id BIGSERIAL PRIMARY KEY,
    competition_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,

    constraint fk_competitions_tags_competition
        foreign key (competition_id) references competitions(id) ON DELETE CASCADE,
    constraint fk_competition_tags_tag
        foreign key (tag_id) references tags(id) ON DELETE CASCADE
);


create table if not exists skills (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


create table if not exists user_skills (
    id  BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,

    constraint fk_user_skills_user
        foreign key (user_id) references users(id) ON DELETE CASCADE,
    constraint fk_user_skills_skill
        foreign key (skill_id) references skills(id) ON DELETE CASCADE
);


create table if not exists competitions_steps (
    id BIGSERIAL PRIMARY KEY,
    competition_id BIGINT NOT NULL,
    name VARCHAR(255),
    description TEXT,
    step_number SMALLINT CHECK ( step_number > 0 ),
    start_date_of_step TIMESTAMP,
    end_date_of_step TIMESTAMP,

    constraint fk_competitions_steps
        foreign key (competition_id) references competitions(id) ON DELETE CASCADE
);

create table if not exists competitions_manager_contacts (
    id BIGSERIAL PRIMARY KEY,
    manager_id BIGINT NOT NULL,
    type_of_contact VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL,
    is_primary BOOLEAN DEFAULT FALSE NOT NULL,

    constraint fk_manager_contacts_manager
        foreign key (manager_id) references competitions_manager(id) ON DELETE CASCADE
);

create table if not exists competitions_blocks (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    competition_id BIGINT NOT NULL,
    content JSONB,
    metadata JSONB,

    constraint fk_competitions_blocks_competition
        foreign key (competition_id) references competitions(id) ON DELETE CASCADE
);

create table if not exists competition_media (
    id BIGSERIAL PRIMARY KEY,
    competition_block_id BIGINT NOT NULL,
    media_id BIGINT NOT NULL,
    position BIGINT NOT NULL CHECK ( position > 0 ),

    constraint fk_competition_media_block
        foreign key (competition_block_id) references competitions_blocks(id) ON DELETE CASCADE,
    constraint fk_comp_media_media
        foreign key (media_id) references media(id) ON DELETE RESTRICT
);

create table if not exists competition_prizes (
    id BIGSERIAL PRIMARY KEY,
    competition_id BIGINT NOT NULL,
    medal_place INTEGER CHECK ( medal_place > 0 ),
    type_of_prize VARCHAR(255) NOT NULL,
    value_of_prize VARCHAR(255),

    constraint fk_competitions_prizes_competition
        foreign key (competition_id) references competitions(id) ON DELETE CASCADE
);

create table if not exists competition_contacts (
    id BIGSERIAL PRIMARY KEY,
    competition_id BIGINT NOT NULL,
    type_of_contact VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL,
    description TEXT,

    constraint fk_manager_contacts_manager
    foreign key (competition_id) references competitions(id) ON DELETE CASCADE
    );

