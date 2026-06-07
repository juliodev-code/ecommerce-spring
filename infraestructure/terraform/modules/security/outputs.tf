output "security_group_id" {
  value = aws_security_group.rds_sg.id
}

output "beanstalk_sg_id" {
  value = aws_security_group.beanstalk_sg.id
}