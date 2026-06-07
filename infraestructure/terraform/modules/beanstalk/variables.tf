variable "vpc_id" {
  description = "VPC ID"
  type        = string
}

variable "subnet_ids" {
    description = "Subnet IDs"
    type = list(string)
}

variable "security_group_id" {
  description = "Security Group ID"
  type        = string
}

variable "db_endpoint" {
  description = "Database Endpoint"
  type        = string
}