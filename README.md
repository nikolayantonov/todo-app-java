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
$ aws s3api create-bucket --bucket java-codepipeline --region eu-west-2 --create-bucket-configuration LocationConstraint=eu-west-2
```

# CloudFormation

* From AWS CLI **CloudFormation command to create the stack**

```bash
$ aws cloudformation package --template-file $INPUT_FILE --output-template-file $OUTPUT_FILE --s3-bucket $S3_BUCKET

$ aws cloudformation deploy --template-file $OUTPUT_FILE --stack-name $STACK_NAME --parameter-overrides StageName=$STAGE_NAME S3BucketName=$S3_BUCKET --capabilities CAPABILITY_IAM
```

# Cleanup

* Delete the S3 Bucker we've created for the artifacts
```bash
$ aws s3api delete-bucket --bucket java-codepipeline --region eu-west-2
```
* Delete CloudFormation stack

# For Codepipeline EKS deployments:

Configure IAM role:
https://eksworkshop.com/codepipeline/role/

Update kubectl configmap:
https://eksworkshop.com/codepipeline/configmap/


# Academic resources

Codepipeline with EKS deployment:
https://eksworkshop.com/codepipeline/codepipeline/

Sourcecode for deployment example:
https://github.com/rnzsgh/eks-workshop-sample-api-service-go/blob/master/buildspec.yml

Ephemeral pipeline with fast Maven build:
https://read.acloud.guru/3-pro-tips-to-speed-up-your-java-based-aws-lambda-continuous-deployment-builds-72310fe18274

Create github personal access token
https://help.github.com/en/articles/creating-a-personal-access-token-for-the-command-line
