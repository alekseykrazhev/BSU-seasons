using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Diagnostics;
using System.Net.Http;
using System.Threading.Tasks;
using Grpc.Net.Client;
using GrpcService1;
using Player = lab1.Models.Player;

namespace lab1.Controllers
{
    public class HomeController : Controller
    {
        Models.CrittersContext crittersContext;
        public HomeController(Models.CrittersContext context)
        {
            crittersContext = context;
        }

        public static DateTime? startDate;
        public static DateTime? endDate;
        
        public async Task<IActionResult> Index()
        {
            var httpHandler = new HttpClientHandler();
            // Return `true` to allow certificates that are untrusted/invalid
            httpHandler.ServerCertificateCustomValidationCallback = 
                HttpClientHandler.DangerousAcceptAnyServerCertificateValidator;
            var channel = GrpcChannel.ForAddress("https://localhost:7175", new GrpcChannelOptions { HttpHandler = httpHandler });
            var client = new Greeter.GreeterClient(channel);
            
            var reply = await client.GetServerResponseAsync(new Message { Position = "-9999", FromDate = "-9999", EndDate = "-9999", Datasource = "roaster"});

            return View( BackToList(reply.Players) );
        }

        [HttpGet]
        public IActionResult Select()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Select(string position, DateTime? startDate, DateTime? endDate)
        {
            var httpHandler = new HttpClientHandler();
            // Return `true` to allow certificates that are untrusted/invalid
            httpHandler.ServerCertificateCustomValidationCallback = 
                HttpClientHandler.DangerousAcceptAnyServerCertificateValidator;
            var channel = GrpcChannel.ForAddress("https://localhost:7175", new GrpcChannelOptions { HttpHandler = httpHandler });
            var client = new Greeter.GreeterClient(channel);

            if (position == null)
            {
                position = "null";
            }
            
            var reply = await client.GetServerResponseAsync(new Message { Position = position, FromDate = startDate.ToString(), EndDate = endDate.ToString(), Datasource = "roaster"});
            
            return RedirectToAction("Temp");
        }

        public async Task<IActionResult> Temp()
        {
            var httpHandler = new HttpClientHandler();
            // Return `true` to allow certificates that are untrusted/invalid
            httpHandler.ServerCertificateCustomValidationCallback = 
                HttpClientHandler.DangerousAcceptAnyServerCertificateValidator;
            var channel = GrpcChannel.ForAddress("https://localhost:7175", new GrpcChannelOptions { HttpHandler = httpHandler });
            var client = new Greeter.GreeterClient(channel);
            
            var reply = await client.GetServerResponseAsync(new Message { Position = "-9999", FromDate = "-9999", EndDate = "-9999", Datasource = "temp"});
            
            return View(BackToList(reply.Players));
        }

        public async Task<IActionResult> Clear(string id)
        {
            
            var httpHandler = new HttpClientHandler();
            // Return `true` to allow certificates that are untrusted/invalid
            httpHandler.ServerCertificateCustomValidationCallback = 
                HttpClientHandler.DangerousAcceptAnyServerCertificateValidator;
            var channel = GrpcChannel.ForAddress("https://localhost:7175", new GrpcChannelOptions { HttpHandler = httpHandler });
            var client = new Greeter.GreeterClient(channel);

            string str = null;
            if (id == null)
            {
                str = "null";
            }
            else
            {
                str = id;
            }
            
            var reply = await client.GetServerResponseAsync(new Message { Position = "-8888", FromDate = str, EndDate = "-9999", Datasource = "temp"});
            
            return RedirectToAction("Temp");
        }

        private IEnumerable<Player> FindPlayer(string playerid)
        {
            if (playerid != null || playerid.Length != 0)
            {
                return crittersContext.roster.Where(predicate: e => e.playerid == playerid);
            }
            else
            {
                return crittersContext.roster.Where(e => e.playerid == playerid);
            }
        }

        [HttpPost]
        public async Task<IActionResult> Save(string playerid, string jersey, string fname, string sname, string position, 
            DateTime birthday, string weight, string height, string birthcity, string birthstate)
        {
            
            var httpHandler = new HttpClientHandler();
            // Return `true` to allow certificates that are untrusted/invalid
            httpHandler.ServerCertificateCustomValidationCallback = 
                HttpClientHandler.DangerousAcceptAnyServerCertificateValidator;
            var channel = GrpcChannel.ForAddress("https://localhost:7175", new GrpcChannelOptions { HttpHandler = httpHandler });
            var client = new Greeter.GreeterClient(channel);
            
            /*
            if(birthday <= endDate && birthday >= startDate)
            {
                var formatted = birthday.ToString("yyyy.MM.dd");
                foreach (var item in FindPlayer(playerid))
                {
                    crittersContext.SaveCritter(item, playerid, Convert.ToInt32(jersey), fname, sname, position, formatted,
                        Convert.ToInt32(weight), Convert.ToInt32(height), birthcity, birthstate);
                }
            }*/

            string formatted = "";
            if (birthday <= endDate && birthday >= startDate)
            {
                formatted = birthday.ToString("yyyy.MM.dd");
            }

            var reply = await client.GetServerResponseAsync(new Message { Position = birthcity, FromDate = formatted, EndDate = "save", Datasource = playerid});

            await Clear(playerid);
            await Select(position, startDate, endDate);
            
            return RedirectToAction("Temp");
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new Models.ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }

        private IEnumerable<Player> BackToList(Google.Protobuf.Collections.RepeatedField<Player_> rep)
        {
            IEnumerable<Player> ans = new List<Player>();
            foreach (var pl in rep)
            {
                var player = new Player();
                player.playerid = pl.Id.ToString();
                player.birthcity = pl.Birthcity;
                player.birthday = DateTime.Parse(pl.Birthday);
                player.birthstate = pl.Birthstate;
                player.fname = pl.Fname;
                player.sname = pl.Sname;
                player.jersey = pl.Jersey;
                player.height = pl.Height;
                player.weight = pl.Weight;
                player.position = pl.Position;
                ans = ans.Append(player);
            }

            return ans;
        }
    }
}
