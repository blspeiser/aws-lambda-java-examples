package io.cambium.aws.lambdas;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import io.cambium.api.LookupService;
import io.cambium.api.impl.LookupServiceBean;
import io.cambium.types.models.LookupData;
import io.cambium.types.requests.GetLookupsRequest;
import io.cambium.types.responses.GetLookupsResponse;

/**
 * GetLookupsLambda.
 * 
 *  A simple Lambda for retrieving a list of lookup options for a dropdown in a web application. 
 *
 * @author Baruch Speiser, Cambium.
 */
public class GetLookupsLambda implements RequestHandler<GetLookupsRequest, GetLookupsResponse> {
  private static final LookupService service = new LookupServiceBean();

  /**
   * Retrieve a list of lookup options.
   *  
   * @param request
   * @param context
   */
  public GetLookupsResponse handleRequest(GetLookupsRequest request, Context context) {
    try {
      List<LookupData> lookups = service.lookup(request.type);
      return new GetLookupsResponse(lookups);
    } catch(RuntimeException e) {
      //Log it before we throw it upwards.  
      log(context, e);
      throw e;
    }
  }

  /**
   * Properly log an exception. 
   * @param context
   * @param e
   */
  private void log(Context context, Exception e) {
    if(null == context) {
      //just log to stderr, I guess
      System.err.println("Error during lookup:");
      e.printStackTrace();
    } else {
      StringWriter sw = new StringWriter();
      sw.append("Error during lookup:\n");
      e.printStackTrace(new PrintWriter(sw));
      context.getLogger().log(sw.toString());
    }
  }

}
