/**
 *
 */

package driver;

import java.io.*;
import java.util.*;

public final class Logger {

	private static final Logger INSTANCE = new Logger(LogLevel.ALL);

	private LogLevel logLevel;
	private PrintStream stream;

	private List<Message> errors;
	private List<Message> warnings;
	private List<Message> infos;

	private Logger(LogLevel logLevel)	{
		this.logLevel = logLevel;
		stream = System.out;

		errors = new ArrayList<>();
		warnings = new ArrayList<>();
		infos = new ArrayList<>();
	}

	public void redirectStream(PrintStream new_stream)	{
		this.stream = new_stream;
	}

	public void error(String formatter, Object... args)	{

		String msg = String.format(formatter, args);

		Message m = new Message(msg,
				new Date(System.currentTimeMillis()),
				LogLevel.ERROR,
				null);

		errors.add(m);
		printMessage(m);
	}

	public void error(Throwable e)	{

		Message m = new Message(e.getMessage(),
				new Date(System.currentTimeMillis()),
				LogLevel.ERROR,
				e);

		errors.add(m);
		printMessage(m);
	}

	public void warn(String formatter, Object... args)	{

		String msg = String.format(formatter, args);

		Message m = new Message(msg,
				new Date(System.currentTimeMillis()),
				LogLevel.WARNING,
				null);

		warnings.add(m);
		printMessage(m);
	}

	public void warn(Throwable e)	{

		Message m = new Message(e.getMessage(),
				new Date(System.currentTimeMillis()),
				LogLevel.WARNING,
				e);

		warnings.add(m);
		printMessage(m);
	}

	public void log(String formatter, Object... args)	{

		String msg = String.format(formatter, args);

		Message m = new Message(msg,
				new Date(System.currentTimeMillis()),
				LogLevel.INFO,
				null);

		infos.add(m);
		printMessage(m);
	}

	public void log(Throwable e)	{

		Message m = new Message(e.getMessage(),
				new Date(System.currentTimeMillis()),
				LogLevel.INFO,
				e);

		infos.add(m);
		printMessage(m);
	}

	public List<Message> allErrors()	{
		return new ArrayList<>(errors);
	}

	public List<Message> allWarnings()	{
		return new ArrayList<>(warnings);
	}

	public List<Message> allInfos()	{
		return new ArrayList<>(infos);
	}

	public static Logger getInstance() {
		return INSTANCE;
	}

	private void printMessage(Message m)	{

		String out = String.format("%s \t[%s] \t%s.", m.getTime().toString(), m.getType().name(), m.getMessage());

		if (m.getCause() != null)
			out += " Caused by: " + m.getCause().toString();

		stream.println(out);
	}

	private class Message	{

		private Date time;
		private Throwable cause;
		private String message;
		private LogLevel type;

		public Message(String msg, Date time, LogLevel type, Throwable cause) {
			this.time = time;
			this.cause = cause;
			this.message = msg;
			this.type = type;
		}

		public Date getTime() {
			return time;
		}

		public void setTime(Date time) {
			this.time = time;
		}

		public Throwable getCause() {
			return cause;
		}

		public void setCause(Throwable cause) {
			this.cause = cause;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public LogLevel getType() {
			return type;
		}

		public void setType(LogLevel type) {
			this.type = type;
		}
	}

	private enum LogLevel	{
		ERROR, WARNING, INFO, ALL
	}
}
