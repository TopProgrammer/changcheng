package site.changcheng.common.http.session;

import java.util.EventListener;

public interface SessionListener extends EventListener {
  public void fire(SessionEvent event);
}

