package closet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionVoid {
 public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
