-- CREATE DATABASE collezioni;
-- CREATE USER collezioni_owner WITH ENCRYPTED PASSWORD '1234';
-- \c "collezioni"
-- GRANT ALL PRIVILEGES ON DATABASE collezioni TO collezioni_owner;
--
-- CREATE SCHEMA collezioni;
--
-- ALTER SCHEMA collezioni OWNER TO collezioni_owner;
-- GRANT ALL PRIVILEGES ON SCHEMA collezioni TO collezioni_owner;
--
-- GRANT CONNECT ON DATABASE collezioni TO collezioni_owner;
-- GRANT USAGE ON SCHEMA collezioni TO collezioni_owner;
--
-- GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA collezioni TO collezioni_owner;
-- GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA collezioni TO collezioni_owner;
-- init.sql
CREATE SCHEMA IF NOT EXISTS collezioni AUTHORIZATION collezioni_owner;

GRANT ALL PRIVILEGES
ON SCHEMA collezioni
  TO collezioni_owner;

-- Assicurati di includere qui eventuali CREATE TABLE, etc.
