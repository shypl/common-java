package org.shypl.common.util.notice;

@FunctionalInterface
public interface NoticeHandler<N> {
	void handleNotice(N notice);
}
