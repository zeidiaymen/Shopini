import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Comments } from '../Models/Comments';
import { ComSService } from '../Services/com-s.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css'],
})
export class CommentsComponent implements OnInit, OnChanges {
  constructor(private serv: ComSService) {
    this.ClientId = (localStorage.getItem('id'));

    console.log(this.ClientId);
  }
  ngOnChanges(changes: SimpleChanges): void {
    //    throw new Error('Method not implemented.');
  }

  ClientId: any;
  data = new Comments();
  list: Comments[] = [];
  dateParse: any[] = [];
  show = true;
  nbComments = 0;
  LikeController = false;
  showMore() {
    this.show = !this.show;
  }

  ngOnInit(): void {
    this.serv.sortList('Newest').subscribe((x: Comments[]): void => {
      console.log(x);
      this.list = x;
      for (let i = 0; i < this.list.length; i++) {
        var d = new Date().getTime() - new Date(this.list[i].date).getTime();

        var h = Math.floor(d * 0.00000027778);
        var m = Math.floor((d * 0.00000027778 - h) * 60);

        var s = Math.ceil(((d * 0.00000027778 - h) * 60 - m) * 60);
        if (m == 0 && h == 0) this.dateParse[i] = s + 's';
        if (h == 0 && m != 0) this.dateParse[i] = m + 'm :' + s + 's';
        else if (h > 0) this.dateParse[i] = h + 'h : ' + m + 'm';
      }
      console.log(this.list[0].c);
    });
    this.serv.getNbreComments().subscribe((x) => (this.nbComments = x));
  }

  sorting(param: any) {
    console.log(param.target.value);
    this.serv.sortList(param.target.value).subscribe((x) => {
      console.log(x);
      this.list = [];
      this.list = x;
      this.dateParse = this.dateParse.reverse();
    });
  }

  postComment() {
    this.data.like = 0;
    console.log(  typeof (this.ClientId))
    this.serv.postComment(this.data, this.ClientId).subscribe((e) => {
      this.serv.sortList('Newest').subscribe((x) => {
        console.log(x);
        this.list = [];
        this.list = x;
      });
      this.serv.getNbreComments().subscribe((x) => (this.nbComments = x));
    });
  }

  increaseLike(id: number) {
    this.serv.increaseLike(id, this.ClientId).subscribe((e) => {
      this.serv.sortList('Newest').subscribe((x) => {
        console.log(x);
        this.list = [];
        this.list = x;
      });
    });
  }
  decreasingLike(id: number) {
    this.serv.decreaseLike(id, this.ClientId).subscribe((e) => {
      this.serv.sortList('Newest').subscribe((x) => {
        console.log(x);
        this.list = [];
        this.list = x;
      });
    });
  }

  deleteComment(id: any, i: any) {
    this.serv.deletecomment(id).subscribe((e) => {
      this.serv.sortList('Newest').subscribe((x) => {
        console.log(x);
        this.list = [];
        this.list = x;
        this.dateParse.splice(i, 1);
      });
      this.serv.getNbreComments().subscribe((x) => (this.nbComments = x));
    });
  }
}
