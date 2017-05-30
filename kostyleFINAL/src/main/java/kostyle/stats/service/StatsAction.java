package stats.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StatsAction {
	public StatsActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
