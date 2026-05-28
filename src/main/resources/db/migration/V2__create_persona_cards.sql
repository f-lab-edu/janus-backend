CREATE TABLE persona_cards (
    id INT PRIMARY KEY,
    code VARCHAR(4) UNIQUE NOT NULL,
    name VARCHAR(32) NOT NULL,
    tagline VARCHAR(128) NOT NULL,
    attributes TEXT[] NOT NULL,
    traits TEXT[] NOT NULL,
    spawn_time VARCHAR(64) NOT NULL,
    how_to_get TEXT NOT NULL,
    image_path VARCHAR(128) NOT NULL,
    speech_bubble VARCHAR(64) NOT NULL,
    special_skills JSONB NOT NULL,
    preset_impulse INT NOT NULL,
    preset_guilt INT NOT NULL,
    preset_happy INT NOT NULL,
    preset_plan INT NOT NULL,
    preset_will INT NOT NULL
);
