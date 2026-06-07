terraform {
  required_version = ">= 1.5"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 6.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
  profile = var.aws_profile
}


module "networking" {
  source = "../../modules/networking"
}

module "security" {
  source = "../../modules/security"
  vpc_id = module.networking.vpc_id
}

module "rds" {
  source = "../../modules/rds"
  db_name = var.db_name
  db_username = var.db_username
  db_password = var.db_password
  subnet_group_name = module.networking.subnet_group_name
  security_group_id = module.security.security_group_id
}

module "beanstalk" {
  source = "../../modules/beanstalk"
  vpc_id     = module.networking.vpc_id
  subnet_ids = module.networking.public_subnet_ids
  db_endpoint = module.rds.endpoint
  security_group_id = module.security.beanstalk_sg_id
}