
resource "aws_s3_bucket" "deployments" {
  bucket = "julio-spring-demo-ecommerce-1234jcjp-4557577"
}

resource "aws_s3_object" "jar" {

  bucket = aws_s3_bucket.deployments.id
  key = "releases/myapp.jar"
  source = "${path.module}/../../../../ecommerce-app/target/ecommerce-app-0.0.1-SNAPSHOT.jar"

  etag = filemd5("${path.module}/../../../../ecommerce-app/target/ecommerce-app-0.0.1-SNAPSHOT.jar")
}




resource "aws_elastic_beanstalk_application" "app" {
  name = "spring-demo"
}

resource "aws_elastic_beanstalk_application_version" "v1" {

  name = "v1"

  application = aws_elastic_beanstalk_application.app.name

  bucket = aws_s3_bucket.deployments.id

  key = aws_s3_object.jar.key
}

resource "aws_iam_role" "beanstalk_ec2_role" {
  name = "beanstalk-ec2-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Effect = "Allow"
      Principal = {
        Service = "ec2.amazonaws.com"
      }
      Action = "sts:AssumeRole"
    }]
  })
}

resource "aws_iam_role_policy_attachment" "web_tier" {
  role       = aws_iam_role.beanstalk_ec2_role.name
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkWebTier"
}

resource "aws_iam_instance_profile" "beanstalk_profile" {
  name = "beanstalk-instance-profile"
  role = aws_iam_role.beanstalk_ec2_role.name
}


resource "aws_elastic_beanstalk_environment" "spring_env" {

  name                = "spring-env"
  application         = aws_elastic_beanstalk_application.app.name
 version_label = aws_elastic_beanstalk_application_version.v1.name
  solution_stack_name = "64bit Amazon Linux 2023 v4.12.1 running Corretto 21"

  setting {
    namespace = "aws:ec2:vpc"
    name      = "VPCId"
    value     = var.vpc_id
  }

  setting {
    namespace = "aws:ec2:vpc"
    name      = "Subnets"
    value     = join(",", var.subnet_ids)
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "SecurityGroups"
    value     = var.security_group_id
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = aws_iam_instance_profile.beanstalk_profile.name
  }
}

