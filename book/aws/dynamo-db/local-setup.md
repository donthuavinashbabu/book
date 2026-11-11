## Local setup
* Download Dynamo DB latest jar from below links
	* Tarball − http://dynamodb-local.s3-website-us-west2.amazonaws.com/dynamodb_local_latest.tar.gz
	* Zip archive − http://dynamodb-local.s3-website-us-west2.amazonaws.com/dynamodb_local_latest.zip
* Extract zip
* Open cmd, navigate to above extracted folder
* Run following command
```
java -Djava.library.path=DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
```