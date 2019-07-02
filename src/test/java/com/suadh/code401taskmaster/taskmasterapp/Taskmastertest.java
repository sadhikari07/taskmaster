package com.suadh.code401taskmaster.taskmasterapp;
    import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
    import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
    import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
    import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
    import javafx.application.Application;
    import org.junit.Before;
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.test.context.ActiveProfiles;
    import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
    import org.springframework.test.context.web.WebAppConfiguration;

    import java.util.List;

    import static org.junit.Assert.assertTrue;

    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringBootTest(classes = TaskmasterappApplication.class)
    @WebAppConfiguration
    @ActiveProfiles("local")
    public class Taskmastertest {

        private DynamoDBMapper dynamoDBMapper;

        @Autowired
        private AmazonDynamoDB amazonDynamoDB;

        @Autowired
        TaskmasterRepository repository;

        @Before
        public void setup() throws Exception {
            dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Taskmaster.class);

            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

            dynamoDBMapper.batchDelete((List<Taskmaster>)repository.findAll());
        }

        @Test
        public void readWriteTestCase() {
            Taskmaster task = new Taskmaster("lab" ,"dynamodb", "finished");
            repository.save(task);

            List<Taskmaster> result = (List<Taskmaster>) repository.findAll();

            assertTrue("Not empty", result.size() > 0);
            assertTrue("Contains task: lab", result.get(0).getTitle().equals("lab"));
        }
    }
