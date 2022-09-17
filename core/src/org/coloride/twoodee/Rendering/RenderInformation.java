package org.coloride.twoodee.Rendering;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class RenderInformation {
    public static class Memory {
        public static double getRamUsagePercentage() {
            final long RAM_TOTAL = Runtime.getRuntime().totalMemory();
            final long RAM_FREE = Runtime.getRuntime().freeMemory();
            final long RAM_USED = RAM_TOTAL - RAM_FREE;
            final double RAM_USED_PERCENTAGE = ((double)RAM_USED / RAM_TOTAL) * 100;

            return RAM_USED_PERCENTAGE;
        }

        public static double getRamFree(boolean mb) {
            final long RAM_TOTAL = Runtime.getRuntime().totalMemory();
            final long RAM_FREE = Runtime.getRuntime().freeMemory();
            final long RAM_FREE_MB = RAM_FREE / 8 / 1024;

            if (mb) {
                return RAM_FREE_MB;
            } else {
                return RAM_FREE;
            }
        }

        public static double getRamUsed(boolean mb) {
            final long RAM_TOTAL = Runtime.getRuntime().totalMemory();
            final long RAM_FREE = Runtime.getRuntime().freeMemory();
            final long RAM_USED = RAM_TOTAL - RAM_FREE;

            final long RAM_USED_MB = RAM_USED / 8 / 1024;

            if (mb) {
                return RAM_USED_MB;
            } else {
                return RAM_USED;
            }
        }

    }
}
