
resource "aws_db_instance" "postgres" {
  identifier = "my-postgres-db"
  engine         = "postgres"
  engine_version = "16.3"
  instance_class = "db.t3.micro"
  allocated_storage = 20
  storage_type      = "gp3"
  db_name  = var.db_name
  username = var.db_username
  password = var.db_password
  db_subnet_group_name   = var.subnet_group_name
  vpc_security_group_ids = [var.security_group_id]
  publicly_accessible = true
  skip_final_snapshot = true
  backup_retention_period = 7
  deletion_protection = false
  tags = {
    Environment = "dev"
    Name        = "postgres-db"
  }
}