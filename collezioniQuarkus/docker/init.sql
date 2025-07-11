CREATE SCHEMA IF NOT EXISTS collezioni AUTHORIZATION collezioni_owner;

GRANT ALL PRIVILEGES
ON SCHEMA collezioni
  TO collezioni_owner;


          -- Concedi privilegi anche sullo schema public
GRANT ALL PRIVILEGES ON SCHEMA public TO collezioni_owner;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO collezioni_owner;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO collezioni_owner;