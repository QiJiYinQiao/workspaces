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
		list.add("����");
		list.add("����");
		list.add("����");
		delegateTask.addCandidateUsers(list);

	}

}
