package tests;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

@SuppressWarnings("serial")
public class TaskListenerImpl implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		List<String> list = new ArrayList<String>();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		delegateTask.addCandidateUsers(list);

	}

}
