package org.shypl.common.util.notice;

import org.shypl.common.util.Cancelable;

public interface NoticeDispatchable {
	<N extends Notice> Cancelable addNoticeHandler(Class<N> noticeClass, NoticeHandler<N> handler);

	<N extends Notice> void removeNoticeHandler(Class<N> noticeClass, NoticeHandler<N> handler);

	<N extends Notice> void removeNoticeHandlers(Class<N> noticeClass);

	void removeNoticeHandler(NoticeHandler<?> handler);

	void removeAllNoticeHandlers();

	<N extends Notice> void dispatchNotice(N notice);
}
