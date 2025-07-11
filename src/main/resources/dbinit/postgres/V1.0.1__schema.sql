-- V1__init_collezioni.sql

-- 1) Schema
CREATE SCHEMA IF NOT EXISTS collezioni;

-- 2) Tabella category

CREATE TABLE IF NOT EXISTS collezioni.category (
                                                id              SERIAL          PRIMARY KEY ,
                                                name_category    VARCHAR(50)     NOT NULL UNIQUE



    );


-- 3) Tabella tipo_beyblade_blades
CREATE TABLE IF NOT EXISTS collezioni.tipo_beyblade_blades (
                                                 id          SERIAL          PRIMARY KEY,
                                                 spin        VARCHAR(50)     NOT NULL,
                                                 type        VARCHAR(50)     ,
                                                 weight      DOUBLE PRECISION ,
                                                 nameBlades  VARCHAR(255)    NOT NULL,
                                                 owned       BOOLEAN,
    -- relazione inverse OneToOne con beyblade: nessuna colonna aggiuntiva qui

    constraint ck_tipo_beyblade_blades_spin check (spin in ('RIGHT',
    'LEFT')),
    constraint ck_tipo_beyblade_blades_type check (type in ('ATTACK',
    'DEFENSE',
    'STAMINA',
    'BALANCE'))
    );

-- 4) Tabella tipo_beyblade_punte
CREATE TABLE IF NOT EXISTS collezioni.tipo_beyblade_punte (
                                                id              SERIAL          PRIMARY KEY,
                                                namePoint       VARCHAR(255)    NOT NULL,
                                                weight          DOUBLE PRECISION,
                                                originBeyFrom   TEXT            ,
                                                owned           BOOLEAN         NOT NULL
    -- relazione inverse OneToOne con beyblade: nessuna colonna aggiuntiva qui
);

-- 5) Tabella tipo_beyblade_ratchet
CREATE TABLE IF NOT EXISTS collezioni.tipo_beyblade_ratchet (
                                                id              SERIAL          PRIMARY KEY,
                                                bumps         INTEGER         NOT NULL,
                                                height        INTEGER         NOT NULL,
                                                weight        DOUBLE PRECISION,
                                                originBeyFrom   TEXT
    -- relazione inverse OneToOne con beyblade: nessuna colonna aggiuntiva qui
);

-- 6) Tabella beyblade
CREATE TABLE IF NOT EXISTS collezioni.beyblade (
                                                id              SERIAL          PRIMARY KEY,
    -- campi ereditati da CollectionItem
                                                name            VARCHAR(255)    NOT NULL,
                                                description     TEXT,
                                                quantity        INTEGER,
                                                -- campi specifici di Beyblade
                                                formato         VARCHAR(50),
                                                ratchet_id      INTEGER,
                                                punta_id        INTEGER,
                                                blades_id       INTEGER,
                                                acquired        BOOLEAN,
                                                owned           BOOLEAN,
                                                wish            BOOLEAN,
                                                category_id     INTEGER,
                                                note            TEXT,
    -- chiavi esterne
                                        CONSTRAINT fk_bb_ratchet
                                        FOREIGN KEY (ratchet_id)
                                        REFERENCES collezioni.tipo_beyblade_ratchet(id),




                                        CONSTRAINT fk_bb_punta
                                        FOREIGN KEY (punta_id)
                                        REFERENCES collezioni.tipo_beyblade_punte(id),

                                        CONSTRAINT fk_bb_blades
                                        FOREIGN KEY (blades_id)
                                        REFERENCES collezioni.tipo_beyblade_blades(id),

                                        CONSTRAINT fk_bb_category
                                        FOREIGN KEY (category_id)
                                        REFERENCES collezioni.category(id),

    constraint ck_beyblade_formato check (formato in ('TAKARA_TOMY',
    'HASBRO'))
);

-- 1) Tabella cards
CREATE TABLE IF NOT EXISTS collezioni.cards (
id              SERIAL           PRIMARY KEY,
-- campi ereditati da CollectionItem
name            VARCHAR(255)     NOT NULL,
description     TEXT,
quantity        INTEGER,
    -- campi specifici di Cards
languageItem    VARCHAR(100),
rarityItem      VARCHAR(50),
aquiredItem     BOOLEAN,
category_id     INTEGER,

    constraint ck_cards_rarityItem check (rarityItem in ('PROMO',
    'RARE',
    'HOLO',
    'RAREHOLO',
    'REVERSEHOLO',
    'ULTRARARE',
    'SECRETRARE',
    'MYTHIC')),
    -- FK
CONSTRAINT fk_cards_category
FOREIGN KEY (category_id)
REFERENCES collezioni.category(id)
    );

-- 2) Tabella pokemon
CREATE TABLE IF NOT EXISTS collezioni.pokemon (
    id SERIAL           PRIMARY KEY,
    buyPrice        DOUBLE PRECISION,
    currentPrice    DOUBLE PRECISION,
    set_carte           VARCHAR(255),
    cardNumber      VARCHAR(100),
    cards_id        INTEGER,
    -- FK
    CONSTRAINT fk_pokemon_cards
    FOREIGN KEY (cards_id)
    REFERENCES collezioni.cards(id)
    );

