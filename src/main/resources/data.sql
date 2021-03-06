insert into quiz (id, question, option1, option2, option3, option4, answer, message) values
(1, 'What is ECR ?',
'A service to allow for GPU compute resources to be allocated',
'A service to store and manage Docker container images',
'A service to monitor performance via graphs',
'A service to run Database As A Service',
'2' ,
'ECR is Elastic Container Registry, which is a dockerhub for aws');

insert into quiz (id, question, option1, option2, option3, option4, answer, message) values
(2, 'What is LightSail ?',
'A really nice fishing boat made of fiberglass and wood',
'A virtual server and ssd, static IP package with a low price',
'A serverless method to run code natively for customers',
'A database on demand for small businesses',
'2' ,
'It is an easy way for some one with no/less cloud computing experience can setup a server in aws environment');

insert into quiz (id, question, option1, option2, option3, option4, answer, message) values
(3, 'What is AWS Elastic Beanstalk ?',
'Easy to use service for deploying and scaling web apps',
'A virtual server environment at a predictable monthly price',
'Durable low cost storage which can be used to archive data',
'A small village just outside of London',
'1' ,
'It is a set of ready-made easy to deployable aws stack');

insert into quiz (id, question, option1, option2, option3, option4, answer, message) values
(4, 'What is Amazon Glacier ?',
'Durable low cost storage which can be used to archive data',
'Provides a hybrid storage service between on-prem and AWS',
'Deploy scale and manage in-memory cache in the cloud',
'Amazon relational database combining speed and simplicity',
'1' ,
'Think of it as a cold storage, where you store data only for archival purpose, mostly for auditing and all');

insert into quiz (id, question, option1, option2, option3, option4, answer, message) values
(5, 'Which AWS service would be the best choice for long term data archival?',
'S3',
'CloudFront',
'EFS',
'Glacier',
'4' ,
'Glacier is the long term archival solution of AWS');

insert into quiz (id, question, option1, option2, option3, option4, answer, message) values
(6, 'Under the shared responsibility model, which of the following is the customer responsible for?',
'Ensuring that disk drives are wiped after use. ',
'Ensuring that firmware is updated on hardware devices.',
'Ensuring that data is encrypted at rest.',
'Ensuring that network cables are category six or higher.',
'3' ,
'Data saved and moved inside aws should be encrypted');

insert into quiz (id, question, option1, option2, option3, option4, answer, message) values
(7, 'What AWS team assists customers with accelerating cloud adoption through paid engagements in any of several specialty practice areas?',
'AWS Enterprise Support',
'AWS Solutions Architects',
'AWS Professional Services',
'AWS Account Managers',
'3' ,
'https://aws.amazon.com/professional-services/');

insert into quiz (id, question, option1, option2, option3, option4, answer, message) values
(8, ' Distributing workloads across multiple Availability Zones supports which cloud architecture design principle?',
'Implement automation',
'Design for agility.',
'Design for failure.',
'Implement elasticity.',
'3' ,
'Think about disaster recovery solution');