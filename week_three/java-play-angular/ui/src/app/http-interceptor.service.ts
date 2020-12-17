import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

/**
 * Module class for application http interceptor.
 * @implements HttpInterceptor
 * @class AppHttpInterceptorService.
 */
@Injectable()
export class AppHttpInterceptorService implements HttpInterceptor {

  constructor() {
  }

  public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next
      .handle(req)
      .pipe(catchError(err => {
        if (err instanceof HttpErrorResponse) {
          this.onError(err);
        }
        return throwError(err);
      }));
  }

  /**
   * Handle http errors.
   * @param response - ErrorResponse.
   */
  private onError(response: HttpErrorResponse): void {
    const clientErrorMessage = this.handleClientSideError(response.status);
    if (clientErrorMessage) {
      // show client side error
      return;
    }

    const serverErrorMessage = this.handleServerError(response.error);
    if (serverErrorMessage) {
      // show server error
    }
  }

  private handleClientSideError(status: number): string {
    switch (status) {
      case 0:
        return 'NO INTERNET CONNECTION';
      case 404:
        return 'REQUEST FAILURE';
      default:
        return;
    }
  }

  private handleServerError(errorResponse: any): string {
    // handle server error
    return '';
  }
}
