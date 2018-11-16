package vn.tki.erp.cambpm.core.helper;

import org.camunda.bpm.engine.test.Deployment;
import vn.tki.erp.cambpm.core.BpmIngegrationTest;

@Deployment
public class BpmEntityConverterTest extends BpmIngegrationTest {
//    private static final String TEST_PROCESS_DEFINITION_KEY = "test";
//
//    private UUID processInstanceId;
//    private BpmEntityConverter entityConverter;
//
//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//        entity = dataManager.create(TestEntity.class);
//        entity.setName("Test entity");
//        entity.setReason("Test reason");
//        dataManager.commit(entity);
//
//        entityConverter = AppBeans.get(BpmEntityConverterImpl.class);
//
//        EntityLoadInfoBuilder loadInfoBuilder = AppBeans.get(EntityLoadInfoBuilder.class);
//        EntityLoadInfo loadInfo = loadInfoBuilder.create(entity);
//        processInstanceId = UUID.fromString(processEngine.getRuntimeService().startProcessInstanceByKey(TEST_PROCESS_DEFINITION_KEY, loadInfo.toString()).getId());
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        super.tearDown();
//        dataManager.remove(entity);
//        processEngine.getRuntimeService().deleteProcessInstance(processInstanceId.toString(), "unit test purpose");
//    }
//
//    @Test
//    public void toBpmTask() {
//        Task task = processEngine.getTaskService().createTaskQuery()
//                .taskName("Phê duyệt")
//                .singleResult();
//        BpmTask bpmTask = entityConverter.toBpmTask(task);
//
//        assertEquals(UUID.fromString(task.getId()),bpmTask.getId());
//        assertEquals("Phê duyệt", bpmTask.getName());
//        assertEquals("test:4:c7833389-c5ef-11e8-9c99-4acd3a513967", bpmTask.getBpmProcessDefinitionVersion().getId());
//    }
}