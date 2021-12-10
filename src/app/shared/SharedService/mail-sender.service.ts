import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class MailSenderService {
  constructor(private http: HttpClient) {}
  sendMail(to: any, from: any, message: any, file: any) {
    return this.http.post(
      '/api/postMail/' + to + '/' + from + '/' + message,
      file
    );
  }
}
