package org.shypl.common.util.notice;

public interface NoticeDispatchable {
	<N extends Notice> NoticeHandlerSub addNoticeHandler(Class<N> noticeClass, NoticeHandler<N> handler);

	<N extends Notice> void removeNoticeHandler(Class<N> noticeClass, NoticeHandler<N> handler);

	void removeNoticeHandler(NoticeHandler<?> handler);

	void removeAllNoticeHandlers();

	<N extends Notice> void dispatchNotice(N notice);
}
