resource "aws_security_group" "rds_sg" {
  name        = "postgres-rds-sg"
  description = "Security group for PostgreSQL"
  vpc_id = var.vpc_id

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    # Better: your office/home IP
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}


resource "aws_security_group" "beanstalk_sg" {
  name   = "beanstalk-sg"
  vpc_id = var.vpc_id

  # Ingress rules to allow incoming web traffic (HTTP and App port)
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    from_port   = 3000
    to_port     = 3000
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    from_port   = 5000
    to_port     = 5000
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  # Egress rule allowing all outbound connections (crucial for downloading updates and checking into AWS APIs)
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group_rule" "beanstalk_to_postgres" {

  type = "ingress"
  from_port = 5432
  to_port   = 5432
  protocol = "tcp"
  security_group_id = aws_security_group.rds_sg.id
  source_security_group_id = aws_security_group.beanstalk_sg.id
}

