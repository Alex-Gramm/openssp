package com.atg.openssp.core.exchange;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atg.openssp.common.demand.ParamValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atg.openssp.common.core.entry.RequestMonitor;
import com.atg.openssp.common.core.entry.SessionAgent;
import com.atg.openssp.common.demand.BidExchange;
import com.atg.openssp.common.exception.RequestException;
import com.atg.openssp.common.logadapter.RequestLogProcessor;
import com.atg.openssp.core.entry.EntryValidator;

/**
 * Handling class for single request handling. Holding all those data which are relevant to a video request and performing demand requests.
 * 
 * Responsible for extracting and evaluating request parameter. Creates a requestid as request identifier, useful in logging.
 * 
 * 
 * @author André Schmer
 *
 */
public class RequestSessionAgent extends SessionAgent {

	private static final Logger log = LoggerFactory.getLogger(RequestSessionAgent.class);

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws RequestException
	 */
	public RequestSessionAgent(final HttpServletRequest request, final HttpServletResponse response) throws RequestException {
		super(request, response);
		RequestLogProcessor.instance.setLogData(this);
		RequestMonitor.monitorRequests();
	}

	@Override
	protected ParamValue createParamValue(HttpServletRequest request) throws RequestException {
		ParamValue paramValue = new EntryValidator().validateEntryParams(request);
		log.debug(paramValue.toString());
		return paramValue;
	}

	@Override
	protected BidExchange createBidExchange() {
		return new BidExchange();
	}

}
