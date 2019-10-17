# Project Architecture

![alt text](https://scratchpad.blog/images/a-serverless-ci-cd-pipeline-for-sam-applications/sam-ci-cd-pipeline.png)

#

# Content
* [S3](#s3)
* [CloudFormation](#cloudformation)
* [Cleanup](#cleanup)
* [Academic Resources](#academic_resources)

# S3

* We create the **S3 Bucket for artifacts**

```bash
$ aws s3api create-bucket --bucket ci-cd-java --region eu-west-2 --create-bucket-configuration LocationConstraint=eu-west-2
```

# CloudFormation

* From AWS CLI **CloudFormation command to create the stack**

```bash
$ aws cloudformation package --template-file $INPUT_FILE --output-template-file $OUTPUT_FILE --s3-bucket $S3_BUCKET

$ aws cloudformation deploy --template-file $OUTPUT_FILE --stack-name $STACK_NAME --parameter-overrides StageName=$STAGE_NAME S3BucketName=$S3_BUCKET --capabilities CAPABILITY_IAM
```


# Academic_Resources

* [SAM Serverless workshop(also API Gateway,, AWS Lamda, Cognito, Host Static Website to S3)](https://aws.amazon.com/getting-started/projects/build-serverless-web-app-lambda-apigateway-s3-dynamodb-cognito/)


# Cleanup

* Delete the S3 Bucker we've created for the artifacts
```bash
$ aws s3api delete-bucket --bucket ci-cd-java --region eu-west-2
```
* Delete the stack created in CloudFormation
#

Learning basic services:
Lambda:

Deploy java package to AWS Lambda:
https://medium.com/swlh/deploying-a-java-package-to-aws-lambda-with-maven-8ecb25ce6443
API Gateway:


Getting started with API Gateway: 
https://docs.aws.amazon.com/apigateway/latest/developerguide/apigateway-getting-started-with-rest-apis.html

Create simple proxy for Lambda using API Gateway:
https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-create-api-as-simple-proxy-for-lambda.html

Tutorial: Lambda over HTTPS:
https://docs.aws.amazon.com/lambda/latest/dg/with-on-demand-https.html

S3:
https://docs.aws.amazon.com/AmazonS3/latest/dev/Welcome.html

aws s3api create-bucket --bucket wildrydes-nikolay-antonov --region eu-west-2 --create-bucket-configuration LocationConstraint=eu-west-2
aws s3api delet-bucket --bucket wildrydes-nikolay-antonov --region eu-west-2 


Codepipeline (optional):
https://docs.aws.amazon.com/codepipeline/latest/userguide/welcome.html

Cloudformation (optional):
https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/Welcome.html

Terraform (optional):
https://www.terraform.io/intro/index.html
SAM:


Theory: https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/what-is-sam.html

Tools:
https://github.com/awslabs/aws-sam-cli


Practice (hello world):
https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-getting-started-hello-world.html

Practice (larger project): 
https://github.com/aws-samples/aws-serverless-samfarm

Jenkins integration with Lambda:



Jenkins SAM plugin:
https://wiki.jenkins.io/display/JENKINS/AWS+SAM+Plugin

Jenkins ci/cd pipeline:
https://digitalvarys.com/how-to-setup-jenkins-cicd-pipeline-for-aws-lambda-with-github-and-sam-template/

Similar to above, more detailed, uses terraform:
https://read.acloud.guru/ci-cd-for-lambda-functions-with-jenkins-1c682a6c8d33

Spring Boot in Lambda:

Unmodified Spring Boot application in Lambda (hard):
https://aws.amazon.com/blogs/opensource/java-apis-aws-lambda/
https://github.com/awslabs/aws-serverless-java-container/wiki/Quick-start---Spring-Boot

Update existing Java Spring Boot application to use Lambda + API Gateway:
https://raymondhlee.wordpress.com/2018/06/11/building-serverless-apis-with-spring-boot-aws-lambda-and-api-gateway/
Easy automation workshop: Website hosting with Lambda + API Gateway:

Example serverless application architecture
https://aws.amazon.com/getting-started/projects/build-serverless-web-app-lambda-apigateway-s3-dynamodb-cognito/

For API Gateway:
Cross-origin resource sharing (CORS) defines a way for client web applications that are loaded in one domain to interact with resources in a different domain. With CORS support, you can build rich client-side web applications with Amazon S3 and selectively allow cross-origin access to your Amazon S3 resources.

Don’t forget to delete : region eu-west-2: 2 cloudformation stacks, s3 bucket: wildrydes-nikolay-antonov, dynamodb table: Ride, iam role: WildRydesLambda, inline policy: DynamoDBWriteAccess, Lambda: RequestUnicorn, API Gateway: WildRydes
CI/CD automation with Lambda:
Jenkins:
https://read.acloud.guru/ci-cd-for-lambda-functions-with-jenkins-1c682a6c8d33
Codepipeline:

Official AWS codepipeline automation tutorial:
https://docs.aws.amazon.com/lambda/latest/dg/build-pipeline.html

Codepipeline automation using cloudformation:
https://github.com/jackstine/lambda-setup-cloudformation

Codepipeline using terraform:
https://github.com/globeandmail/aws-codepipeline-lambda
Full scope automation:
https://github.com/retrofuturejosh/lambda-pipeline
Real deployment considerations:
Ephemeral pipeline with fast Maven build:
https://read.acloud.guru/3-pro-tips-to-speed-up-your-java-based-aws-lambda-continuous-deployment-builds-72310fe18274

Lambda limits:
https://docs.aws.amazon.com/lambda/latest/dg/limits.html

Deployment:

Don’t forget to purge: Travelapp-lambda-codedeploy-role, codedeploy, cloudformation, ssm secret

Create github personal access token
https://help.github.com/en/articles/creating-a-personal-access-token-for-the-command-line
Token: <token string>

Clone project: https://github.com/jenseickmeyer/todo-app-nodejs

Specify runtime version in buildspec.yaml for codebuild:
https://docs.aws.amazon.com/codebuild/latest/userguide/troubleshooting.html#troubleshooting-build-must-specify-runtime
https://docs.aws.amazon.com/codebuild/latest/userguide/build-spec-ref.html#runtime-versions-buildspec-file

Create public S3 bucket and copy this file into it:

https://s3.eu-central-1.amazonaws.com/com.carpinuslabs.cloudformation.templates/ci-cd/sam-build-pipeline.yaml

Create stack (aws cloudformation create-stack as describe below):
https://scratchpad.blog/devops/a-serverless-ci-cd-pipeline-for-sam-applications/

Deploy Lambda/API Gateway:

aws cloudformation package --template-file $INPUT_FILE --output-template-file $OUTPUT_FILE --s3-bucket $S3_BUCKET
aws cloudformation deploy --template-file $OUTPUT_FILE --stack-name $STACK_NAME --parameter-overrides StageName=$STAGE_NAME S3BucketName=$S3_BUCKET --capabilities CAPABILITY_IAM

Test api gateway with curl -i <api gateway>





