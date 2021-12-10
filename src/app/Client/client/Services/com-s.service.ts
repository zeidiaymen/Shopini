import { HttpClient } from '@angular/common/http';
import { identifierModuleUrl } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Comments } from '../Models/Comments';

@Injectable({
  providedIn: 'root',
})
export class ComSService {
  constructor(private http: HttpClient) {}

  getComments() {
    return this.http.get<Comments[]>('/api/getComments');
  }
  getNbreComments() {
    return this.http.get<number>('/api/nbComments');
  }
  sortList(param: String) {
    return this.http.get<Comments[]>('/api/sortList/' + param);
  }
  postComment(data: Comments, id: any) {
    return this.http.post<Comments>('/api/postComment/' + id, data);
  }
  deletecomment(id: number) {
    return this.http.delete('/api/deleteComment/' + id);
  }
  increaseLike(id: number, idC: any) {
    return this.http.post('/api/increaseLike/' + id + '/' + idC, null);
  }
  decreaseLike(id: number, idC: any) {
    return this.http.post('/api/decreaseLike/' + id + '/' + idC, null);
  }
}
