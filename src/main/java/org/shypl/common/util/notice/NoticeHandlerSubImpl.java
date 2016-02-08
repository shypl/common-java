package org.shypl.common.util.notice;

class NoticeHandlerSubImpl<N extends Notice> implements NoticeHandlerSub {
	private final NoticeDispatcher dispatcher;
	private final Class<N>         noticeClass;
	private final NoticeHandler<N> handler;

	public NoticeHandlerSubImpl(NoticeDispatcher dispatcher, Class<N> noticeClass, NoticeHandler<N> handler) {
		this.dispatcher = dispatcher;
		this.noticeClass = noticeClass;
		this.handler = handler;
	}

	@Override
	public void remove() {
		dispatcher.removeNoticeHandler(noticeClass, handler);
	}
}
