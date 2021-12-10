import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Client } from '../Models/Client';
import { ClientService } from '../Services/client.service';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css'],
})
export class InfoComponent implements OnInit {
  client!: Client;
  info: any;
  constructor(private r: ActivatedRoute, private serv: ClientService) {}

  ngOnInit(): void {
    this.r.paramMap.subscribe((data) => {
      this.serv.getClientById(data.get('id')).subscribe((e) => {
        this.client = e;
      console.log(e)
      });
      this.info = data.get('info');
    });
  }
}
