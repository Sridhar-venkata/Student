package com.tyss.Student.MDC;

import com.tyss.Student.Configuration.UniqueIdFilterConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UniqueIdFilter extends OncePerRequestFilter {
//  private final String responseHeader;
//  private final String mdcKey;
//  private final String requestHeader;
//
//  public UniqueIdFilter()
//  {
//    responseHeader= UniqueIdFilterConfigurat
//  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

  }
}
