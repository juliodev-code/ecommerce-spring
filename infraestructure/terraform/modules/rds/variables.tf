variable "aws_profile" {
  description = "AWS Profile"
  type        = string
  default     = "terraform"
}

variable "db_name" {
  type = string
}

variable "db_username" {
  type = string
}

variable "db_password" {
  type      = string
  sensitive = true
}