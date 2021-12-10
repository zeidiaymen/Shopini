import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FactStatsService } from '../Service/fact-stats.service';
declare var google:any ;
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  @ViewChild('pieChart') pieChart!: ElementRef;
  tun=0 ;
  fr =0 ;
  al =0 ;
  lb =0 ;
  ma =0 ;
    drawChart = () => {
  
  
      const data = google.visualization.arrayToDataTable([
        ['Deliveries', 'Deliveries by country'],
        ['Tunisie', this.tun ],
        ['France', 2],
        ['Algerie', 3],
        ['Libye', 5],
        ['Maroc', 10]
      ]);
      
      
      const datas = google.visualization.arrayToDataTable([
        ['Month', 'Taken', 'Expired'],
        ['October',  this.Tsep,      this.Esep],
        ['November',  this.Tnov,      this.Enov],
        ['December', this.Tdec,       this.Edec],
        ['January',  this.Tjan,      this.Ejan]
      ]);
  
  
  
      const options = {
        title: 'distribution of deliveries by country',
        legend: {position: 'top'}
  
  
      };
      const options1 = {
        title: 'Status of deliveries by month',
        curveType: 'function',
        legend: { position: 'bottom' }
      };
  
  
      const chart = new google.visualization.PieChart(this.pieChart.nativeElement);
  
      const charts = new google.visualization.LineChart(this.lineChart.nativeElement);
      chart.draw(data, options);
      charts.draw(datas, options1);
  
    }
    Tsep = 1000 ;
    Esep = 1000 ;
    Tnov = 1000 ;
    Enov = 1000 ;
    Tdec = 1000 ;
    Edec = 1000 ;
    Tjan = 1000 ;
    Ejan = 1000 ;
  
  
  
    //second chart 
  @ViewChild('lineChart') lineChart!: ElementRef;
  
  
    constructor(private serv:FactStatsService) { }
  
    ngOnInit(): void {
      this.serv.getDelivery().subscribe(e=> { 
  console.log(e)
      e.forEach(x=> {
        if ( x.address == "TUNISIE")
        this .tun ++  ; 
        else  if (x.address == "MAROC")
        this .ma ++ ;
        else  if (x.address == "LIBYE")
        this .lb ++ ;
        else  if (x.address == "FRANCE")
        this .fr ++ ;
        else 
        this.al ++ ;
        var t = new Date (x.estimatedTime);
        console.log(t.getMonth())
        if (t.getMonth() == 11)
        {
          if (x.status == "expired")
      this.Enov +=100;
        
        else 
        this. Tnov +=100; 
        }
        else     if (t.getMonth() == 12)
        {
          if (x.status == "expired")
      this.Esep +=100;
        
        else 
        this. Tsep +=100; 
        }
        else     if (t.getMonth() == 12)
        {
          if (x.status == "expired")
      this.Edec +=100;
        
        else 
        this. Tdec +=100; 
        }
        else 
        {
          if (x.status == "expired")
          this.Ejan +=100;
            
            else 
            this. Tjan +=100; 
            }
        }
      
      )
      })
  
    }
  
    ngAfterViewInit(): void {
      google.charts.load('current', {packages: ['corechart'] });
      google.charts.setOnLoadCallback(this.drawChart);
      google.charts.load('current', {packages: ['corechart', 'line']});
      google.charts.setOnLoadCallback(this.drawChart);
    }

}
