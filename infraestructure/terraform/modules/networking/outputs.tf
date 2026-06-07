output "vpc_id" {
  value = aws_vpc.main.id
}

output "public_subnet_ids" {
  value       = [aws_subnet.public_1.id, aws_subnet.public_2.id]
  description = "Public subnet IDs for EC2 instances"
}

output "subnet_group_name" {
  value = aws_db_subnet_group.main.name
}
