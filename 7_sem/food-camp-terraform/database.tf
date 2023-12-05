resource "google_sql_database" "database_deletion_policy" {
  name     = "foodcamp-db-main"
  instance = google_sql_database_instance.instance.name
  deletion_policy = "ABANDON"
}

resource "google_sql_database_instance" "instance" {
  name             = "foodcamp-db-1"
  region           = "us-central1"
  database_version = "MYSQL_8_0"
  settings {
    tier = "db-g1-small"
  }

  deletion_protection  = "true"
}
