using Microsoft.AspNetCore.Mvc;
using lab1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Diagnostics;

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

        [NonAction]
        private IEnumerable<Player> SelectPlayers(string position, DateTime? startDate, DateTime? endDate)
        {
            HomeController.startDate = startDate;
            HomeController.endDate = endDate;
            if (!startDate.HasValue)
            {
                startDate = DateTime.MinValue;
            }
            if (!endDate.HasValue)
            {
                endDate = DateTime.MaxValue;
            }

            if (position == null || position.Length == 0)
            {
                return crittersContext.roster.Where(e => startDate <= e.birthday && endDate >= e.birthday);
            } 
            else
            {
                return crittersContext.roster.Where(
                    predicate: e => e.position == position && startDate <= e.birthday && endDate >= e.birthday
                );
            }
        }
        public IActionResult Index()
        {
            return View(crittersContext.roster.ToList());
        }

        [HttpGet]
        public IActionResult Select()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Select(string position, DateTime? startDate, DateTime? endDate)
        {
            crittersContext.ClearTemp();
            foreach (var rosterPlayer in SelectPlayers(position, startDate, endDate))
            {
                crittersContext.MoveToTemp(rosterPlayer);
            }
            return RedirectToAction("Temp");
        }

        public IActionResult Temp()
        {
            return View(crittersContext.temp.ToList());
        }

        public IActionResult Clear(string id)
        {
            if (id == null)
            {
                crittersContext.MoveAllToRoster();
            }
            else
            {
                crittersContext.MoveToRoster(crittersContext.temp.Single(e => e.playerid == id));
            }
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
        public IActionResult Save(string playerid, string jersey, string fname, string sname, string position, 
            DateTime birthday, string weight, string height, string birthcity, string birthstate)
        {
            if(birthday <= endDate && birthday >= startDate)
            {
                var formatted = birthday.ToString("yyyy.MM.dd");
                foreach (var item in FindPlayer(playerid))
                {
                    crittersContext.SaveCritter(item, playerid, Convert.ToInt32(jersey), fname, sname, position, formatted,
                    Convert.ToInt32(weight), Convert.ToInt32(height), birthcity, birthstate);
                }
            }

            Clear(playerid);
            Select(position, startDate, endDate);
            
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
    }
}
