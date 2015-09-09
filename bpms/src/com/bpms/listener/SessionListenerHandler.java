package com.bpms.listener;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import net.sf.ehcache.CacheManager;

@SuppressWarnings("deprecation")
public class SessionListenerHandler implements HttpSessionListener,
		HttpSessionBindingListener, HttpSession

{

	public void sessionCreated(HttpSessionEvent se) {

	}

	public void sessionDestroyed(HttpSessionEvent se) {
		CacheManager.getInstance().clearAll();

	}

	public void valueBound(HttpSessionBindingEvent arg0) {

	}

	public void valueUnbound(HttpSessionBindingEvent arg0) {

	}

	public Object getAttribute(String name) {
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Enumeration getAttributeNames() {
		return null;
	}

	public long getCreationTime() {
		return 0;
	}

	public String getId() {
		return null;
	}

	public long getLastAccessedTime() {
		return 0;
	}

	public int getMaxInactiveInterval() {
		return 0;
	}

	public ServletContext getServletContext() {
		return null;
	}

	public HttpSessionContext getSessionContext() {
		return null;
	}

	public Object getValue(String name) {
		return null;
	}

	public String[] getValueNames() {
		return null;
	}

	public void invalidate() {
		CacheManager.getInstance().clearAll();

	}

	public boolean isNew() {
		return false;
	}

	public void putValue(String name, Object value) {

	}

	public void removeAttribute(String name) {

	}

	public void removeValue(String name) {

	}

	public void setAttribute(String name, Object value) {

	}

	public void setMaxInactiveInterval(int interval) {

	}

}
