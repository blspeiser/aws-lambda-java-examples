package io.cambium.aws.lambdas;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import io.cambium.api.LookupsService;
import io.cambium.types.models.LookupData;
import io.cambium.types.requests.GetLookupsRequest;
import io.cambium.types.responses.GetLookupsResponse;
import io.cambium.utils.ObjectFactory;

/**
 * GetLookupsLambda.
 * 
 *  A simple Lambda for retrieving a list of lookup options for a dropdown in a web application. 
 *
 * @author Baruch Speiser, Cambium.
 */
public class GetLookupsLambda implements RequestHandler<GetLookupsRequest, GetLookupsResponse> {
  private static final LookupsService service = ObjectFactory.getLookupsService();

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
      e.printStackTrace(System.err);
    } else {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      pw.println("Error during lookup:");
      e.printStackTrace(pw);
      context.getLogger().log(sw.toString());
    }
  }

}
